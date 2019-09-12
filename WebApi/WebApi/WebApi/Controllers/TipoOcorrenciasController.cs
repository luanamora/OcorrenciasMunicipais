using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using WebApi.Models;

namespace WebApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class TipoOcorrenciasController : ControllerBase
    {
        private readonly ocorrenciasdbContext _context;

        public TipoOcorrenciasController(ocorrenciasdbContext context)
        {
            _context = context;
        }

        // GET: api/TipoOcorrencias
        [HttpGet]
        public IEnumerable<TipoOcorrencia> GetTipoOcorrencia()
        {
            return _context.TipoOcorrencia;
        }

        // GET: api/TipoOcorrencias/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetTipoOcorrencia([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var tipoOcorrencia = await _context.TipoOcorrencia.FindAsync(id);

            if (tipoOcorrencia == null)
            {
                return NotFound();
            }

            return Ok(tipoOcorrencia);
        }

        // PUT: api/TipoOcorrencias/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutTipoOcorrencia([FromRoute] int id, [FromBody] TipoOcorrencia tipoOcorrencia)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != tipoOcorrencia.CdTipoocorrencia)
            {
                return BadRequest();
            }

            _context.Entry(tipoOcorrencia).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!TipoOcorrenciaExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/TipoOcorrencias
        [HttpPost]
        public async Task<IActionResult> PostTipoOcorrencia([FromBody] TipoOcorrencia tipoOcorrencia)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.TipoOcorrencia.Add(tipoOcorrencia);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetTipoOcorrencia", new { id = tipoOcorrencia.CdTipoocorrencia }, tipoOcorrencia);
        }

        // DELETE: api/TipoOcorrencias/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteTipoOcorrencia([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var tipoOcorrencia = await _context.TipoOcorrencia.FindAsync(id);
            if (tipoOcorrencia == null)
            {
                return NotFound();
            }

            _context.TipoOcorrencia.Remove(tipoOcorrencia);
            await _context.SaveChangesAsync();

            return Ok(tipoOcorrencia);
        }

        private bool TipoOcorrenciaExists(int id)
        {
            return _context.TipoOcorrencia.Any(e => e.CdTipoocorrencia == id);
        }
    }
}