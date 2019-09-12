using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class Ocorrencia
    {
        public Ocorrencia()
        {
            HistoricoOcorrencia = new HashSet<HistoricoOcorrencia>();
            ImagemOcorrencia = new HashSet<ImagemOcorrencia>();
        }

        public int CdOcorrencia { get; set; }
        public int CdUsuario { get; set; }
        public int CdPrioridade { get; set; }
        public int CdTipoocorrencia { get; set; }
        public int CdAreaatendimento { get; set; }
        public int CdEndereco { get; set; }
        public int CdEstadoocorrencia { get; set; }
        public int NrOcorrencia { get; set; }
        public string DsMensagem { get; set; }
        public string DsObservacao { get; set; }
        public int NrStatus { get; set; }
        public bool DsFinalizado { get; set; }
        public int? NrCurtir { get; set; }
        public DateTime DtCadastro { get; set; }
        public DateTime? DtAtualizacao { get; set; }

        public AreaAtendimento CdAreaatendimentoNavigation { get; set; }
        public Endereco CdEnderecoNavigation { get; set; }
        public EstadoOcorrencia CdEstadoocorrenciaNavigation { get; set; }
        public PrioridadeOcorrencia CdPrioridadeNavigation { get; set; }
        public TipoOcorrencia CdTipoocorrenciaNavigation { get; set; }
        public Usuario CdUsuarioNavigation { get; set; }
        public ICollection<HistoricoOcorrencia> HistoricoOcorrencia { get; set; }
        public ICollection<ImagemOcorrencia> ImagemOcorrencia { get; set; }
    }
}
