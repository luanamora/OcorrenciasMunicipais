using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class EstadoOcorrencia
    {
        public EstadoOcorrencia()
        {
            Ocorrencia = new HashSet<Ocorrencia>();
        }

        public int CdEstadoocorrencia { get; set; }
        public string DsEstadoocorrencia { get; set; }
        public DateTime DtCadastro { get; set; }
        public DateTime? DtAtualizacao { get; set; }

        public ICollection<Ocorrencia> Ocorrencia { get; set; }
    }
}
